package com.ifox.service;

import org.springframework.stereotype.Service;

@Service
public interface SnapshotService {
    String getSnapshot(String historyData, String id);
}
