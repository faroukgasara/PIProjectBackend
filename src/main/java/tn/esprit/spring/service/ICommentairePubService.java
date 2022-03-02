package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.CommentairePub;
import tn.esprit.spring.entity.Publication;

public interface ICommentairePubService {
	public void AjouterCommentare(CommentairePub com,Long idPub,String email) ;
	public List<CommentairePub> getCommentairePub();
	public void deleteCom(Long id);
	public void updateCom(CommentairePub comP);
	public int nbreLikeParCmnt(Long id);


}
