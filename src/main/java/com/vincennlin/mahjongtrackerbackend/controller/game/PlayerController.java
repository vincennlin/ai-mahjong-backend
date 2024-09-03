package com.vincennlin.mahjongtrackerbackend.controller.game;

import com.vincennlin.mahjongtrackerbackend.constant.page.PageConstants;
import com.vincennlin.mahjongtrackerbackend.payload.game.page.GamePageResponse;
import com.vincennlin.mahjongtrackerbackend.payload.game.page.PlayerPageResponse;
import com.vincennlin.mahjongtrackerbackend.payload.game.request.CreatePlayerRequest;
import com.vincennlin.mahjongtrackerbackend.payload.game.dto.PlayerDto;
import com.vincennlin.mahjongtrackerbackend.service.game.PlayerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/players")
    public ResponseEntity<PlayerPageResponse> getPlayers(
            @RequestParam(name = "pageNo", defaultValue = PageConstants.DEFAULT_PAGE_NUMBER, required = false) @Min(0) Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = PageConstants.DEFAULT_PAGE_SIZE, required = false) @Max(100) @Min(1) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = PageConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = PageConstants.DEFAULT_SORT_DIR, required = false) String sortDir) {

        Pageable pageable = getPageable(pageNo, pageSize, sortBy, sortDir);

        PlayerPageResponse playerPageResponse = playerService.getPlayers(pageable);

        return new ResponseEntity<>(playerPageResponse, HttpStatus.OK);
    }

    @PostMapping("/players")
    public ResponseEntity<PlayerDto> createPlayer(@Valid @RequestBody CreatePlayerRequest request) {

        PlayerDto playerDto = playerService.createPlayer(request);

        return new ResponseEntity<>(playerDto, HttpStatus.CREATED);
    }

    private Pageable getPageable(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {
        return PageRequest.of(pageNo, pageSize,
                sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
    }
}
