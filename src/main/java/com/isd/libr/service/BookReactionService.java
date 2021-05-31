package com.isd.libr.service;

import com.isd.libr.web.dto.requests.BookReactionRequest;

public interface BookReactionService {
    void react(BookReactionRequest bookReactionRequest);
}
