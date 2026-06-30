package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import model.ProfesionalServicio;
import persistence.exceptions.NonexistentEntityException;

public class ProfesionalServicioJpaController implements Serializable {

    public ProfesionalServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public ProfesionalServicioJpaController(){
        emf = Persistence.createEntityManagerFactory("sistemaReservasJPAPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProfesionalServicio profesionalServicio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(profesionalServicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProfesionalServicio profesionalServicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            profesionalServicio = em.merge(profesionalServicio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = profesionalServicio.getId();
                if (findProfesionalServicio(id) == null) {
                    throw new NonexistentEntityException("The profesionalServicio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProfesionalServicio profesionalServicio;
            try {
                profesionalServicio = em.getReference(ProfesionalServicio.class, id);
                profesionalServicio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The profesionalServicio with id " + id + " no longer exists.", enfe);
            }
            em.remove(profesionalServicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProfesionalServicio> findProfesionalServicioEntities() {
        return findProfesionalServicioEntities(true, -1, -1);
    }

    public List<ProfesionalServicio> findProfesionalServicioEntities(int maxResults, int firstResult) {
        return findProfesionalServicioEntities(false, maxResults, firstResult);
    }

    private List<ProfesionalServicio> findProfesionalServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProfesionalServicio.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ProfesionalServicio findProfesionalServicio(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProfesionalServicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getProfesionalServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProfesionalServicio> rt = cq.from(ProfesionalServicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
