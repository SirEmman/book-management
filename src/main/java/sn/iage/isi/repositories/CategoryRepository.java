package sn.iage.isi.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import sn.iage.isi.entities.Category;

import java.util.List;

public class CategoryRepository {
    EntityManager em  = JpaUtil.getEntityManager();
    public Category create(Category category) {
        EntityTransaction tx = em.getTransaction();
        Category c = Category.builder()
                .name(category.getName())
                .state(Boolean.TRUE)
                .build();

        c.setUserCreated("Mrrvh");
        c.setUserUpdated("Mrrvh");
        try {
            tx.begin(); //Debut de la transaction
            em.persist(c);
            tx.commit(); //validation de la transaction

        }catch (Exception e) {
            tx.rollback();//annulation de la transaction
        }
        return c;
    }
    public int countCategories(){
        return em.createQuery("SELECT COUNT(c.id) FROM Category c", Integer.class).getSingleResult();
    }
    public List<Category> getAll() {
        //return em.createQuery("SELECT c FROM Category  c ORDER BY c.name ASC ", Category.class).getResultList();
        return em.createNamedQuery("Category.findAll", Category.class).getResultList();
    }
}
