package com.isd.libr.service;

import com.isd.libr.web.dto.requests.CommentReactionRequest;

public interface CommentReactionService {
    void react(CommentReactionRequest commentReactionRequest);
}
