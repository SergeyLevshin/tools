package com.toolsapp.rest.extra;

import com.toolsapp.domain.extra.Worker;
import com.toolsapp.service.extra.worker.WorkerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rest/worker")
public class RestWorkerController {

    private final WorkerService service;

    public RestWorkerController(WorkerService service) {
        this.service = service;
    }

    @GetMapping("/workers")
    public ResponseEntity<List<Worker>> getAllWorkers() {
        final List<Worker> workers = service.findAllSortByName();
        return workers != null && !workers.isEmpty()
                ? new ResponseEntity<>(workers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/workers/{id}")
    public ResponseEntity<Worker> getSingleWorker(@PathVariable("id") long id) {
        Optional<Worker> worker = service.findById(id);
        return worker.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/removeTool/{id}")
    public ResponseEntity<?> removeToolFromWorker(@PathVariable("id") long workerId,
                                       @RequestParam long toolId,
                                       @RequestParam int quantity){
        return service.removeToolFromWorker(workerId, toolId, quantity)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
