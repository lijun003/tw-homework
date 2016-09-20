package com.ifox.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.ifox.domain.Data;

@Service
public interface SnapshotService {
    String getSnapshot(List<Data> datas, String id);
}
