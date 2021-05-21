package com.isd.libr.service;

import com.isd.libr.web.dto.requests.AddCommentRequest;

public interface CommentService {
    void addComment(AddCommentRequest request);
}
