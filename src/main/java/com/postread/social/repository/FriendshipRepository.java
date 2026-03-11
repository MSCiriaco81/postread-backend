package com.postread.social.repository;

import com.postread.social.model.Friendship;
import com.postread.social.model.Friendship.FriendshipStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FriendshipRepository extends MongoRepository<Friendship, String> {

    @Query("{ $or: [ { requesterId: ?0, receiverId: ?1 }, { requesterId: ?1, receiverId: ?0 } ] }")
    Optional<Friendship> findBetweenUsers(String userId1, String userId2);

    @Query("{ $or: [ { requesterId: ?0 }, { receiverId: ?0 } ], status: ?1 }")
    List<Friendship> findByUserAndStatus(String userId, FriendshipStatus status);

    List<Friendship> findByReceiverIdAndStatus(String receiverId, FriendshipStatus status);
}
