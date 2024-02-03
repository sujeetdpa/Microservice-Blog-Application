package com.assignment.aspd.reactionservice.repository;

import com.assignment.aspd.reactionservice.model.Reaction;
import com.assignment.aspd.reactionservice.model.TargetType;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction,Long> {
    List<Reaction> findByTargetIdAndTargetType(Long targetId, TargetType targetType);
}
