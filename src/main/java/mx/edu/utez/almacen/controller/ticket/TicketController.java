package mx.edu.utez.almacen.controller.ticket;

import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.controller.ticket.dto.TicketDto;
import mx.edu.utez.almacen.service.ticket.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class TicketController {
    @RestController
    @RequestMapping("/api/ticket")
    @CrossOrigin({"*"})

    public class ProductController {
        private final TicketService service;

        public ProductController(TicketService service) {
            this.service = service;
        }


        @GetMapping("getAll")
        public ResponseEntity<ApiResponse> getAll(){
            return service.getAll();
        }

        @PostMapping("save")
        public  ResponseEntity<ApiResponse> save(@RequestBody TicketDto dto){
            return service.save(dto.toEntity());
        }

        @PutMapping("update")
        public  ResponseEntity<ApiResponse> update(@RequestBody TicketDto dto){
            return  service.update(dto.toEntityId());
        }
        @DeleteMapping("delete/{id}")
        public  ResponseEntity<ApiResponse> delete(@PathVariable Long id){
            return  service.delete(id);
        }
    }
}
