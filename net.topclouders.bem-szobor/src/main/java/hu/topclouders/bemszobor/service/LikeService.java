package hu.topclouders.bemszobor.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class LikeService {

	public void likePost(Long protestId, Integer postId) {

	}
}
