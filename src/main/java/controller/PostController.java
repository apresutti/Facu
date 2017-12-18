package controller;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Image;
import model.Post;
import model.User;

@Stateless
public class PostController {

    @PersistenceContext
    private EntityManager entityManager;

	public void generate(User user, String content, Image img) {
		Post p = new Post();
		p.setDateTime(new Date());
		p.setContent(content);
		p.setUser(user);
		p.setImage(img);
		entityManager.persist(p);
	}

	public List<Post> obtain(User user){
		TypedQuery<Post> q = entityManager.createQuery("Select p from Post p where p.user = :user order by p.id desc",Post.class);
		q.setParameter("user",user);
		return q.getResultList();
	}
	
	public List<Post> obtainAll(User user){
		TypedQuery<Post> q = entityManager.createQuery("Select p from Post p, Follower f where p.id = f.id and f.id = :user   order by p.id desc",Post.class);
		return q.getResultList();
	}

}
