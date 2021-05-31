package com.isd.libr.service;

import com.isd.libr.web.dto.requests.BookReactionRequest;
import com.isd.libr.web.entity.ReactionType;

public interface BookReactionService {
    void react(BookReactionRequest bookReactionRequest);
    Integer countReactionTypes(ReactionType type);
}
