package com.koreait.day3.ifs;

import com.koreait.day3.model.network.Header;

public interface CrudInterface<Req, Res> {
    Header<Res> create(Header<Req> request);

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header<Res> delete(Long id);
}
