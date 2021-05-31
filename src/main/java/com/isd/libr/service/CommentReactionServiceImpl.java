package com.isd.libr.service;

import com.isd.libr.repo.CommentReactionRepository;
import com.isd.libr.repo.CommentRepository;
import com.isd.libr.repo.UserRepository;
import com.isd.libr.web.dto.requests.CommentReactionRequest;
import com.isd.libr.web.entity.Comment;
import com.isd.libr.web.entity.CommentReaction;
import com.isd.libr.web.entity.ReactionType;
import com.isd.libr.web.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentReactionServiceImpl implements CommentReactionService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommentReactionRepository commentReactionRepository;


    @Override
    public void react(CommentReactionRequest commentReactionRequest) {
        long userId = commentReactionRequest.getUserId();
        long commentId = commentReactionRequest.getCommentId();
        ReactionType reactionType = commentReactionRequest.getType();
        Optional<Comment> comment = commentRepository.findById(commentId);

        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("User with ID [%s] not found", commentReactionRequest.getUserId()));
        }

        CommentReaction commentReaction = CommentReaction.builder()
                .comment(comment.get())
                .user(user.get())
                .reactionType(reactionType)
                .build();
        commentReactionRepository.save(commentReaction);
    }
}
