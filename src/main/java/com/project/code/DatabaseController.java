package com.project.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DatabaseController {

    @Autowired
    private DatabaseService databaseService;

    @GetMapping("/databases")
    public List<String> getDatabases() {
        return databaseService.listDatabases();
    }

    @GetMapping("/databases/{dbName}/tables")
    public List<String> getTables(@PathVariable String dbName) {
        return databaseService.listTables(dbName);
    }
}