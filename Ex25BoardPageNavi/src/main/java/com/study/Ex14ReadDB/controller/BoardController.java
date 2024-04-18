package com.study.Ex14ReadDB.controller;

import com.study.Ex14ReadDB.domain.board.Board;
import com.study.Ex14ReadDB.dto.BoardResponseDto;
import com.study.Ex14ReadDB.dto.BoardSaveRequestDto;
import com.study.Ex14ReadDB.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // localhost:8080/board/
    @GetMapping("/")
    public String main(){
        return "redirect:/board/listForm";
        //servlet(JSP) request.forward() : request내장변수 간직, 주소줄 안바뀜.
        //             response.redirect() : request내장변수 소멸, 주소줄 바뀜.
    }

    @GetMapping("/listForm")
    public String listForm(Model model,
                           //localhost:8080/board/listForm?page=0
                           @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Board> paging = boardService.getList( page );
        model.addAttribute("paging", paging);

        List<BoardResponseDto> list = new ArrayList<>();
        for( Board entity : paging ) {
            list.add( new BoardResponseDto(entity) );
        }
        model.addAttribute("list", list);

        return "listForm"; //listForm.html로 응답
    }

    @GetMapping("/writeForm")
    public String writeForm(){
        return "writeForm"; //writeForm.html로 응답
    }
    //Form의 Input데이터를 가져오는 방법
    //1. @RequestParam
    //2. 클래스객체(DTO/VO) 매핑 : 기본생성자, Setter 필요
    //3. 맵(Map) 매핑
    @PostMapping("/writeAction")
    @ResponseBody
    public String writeAction(@ModelAttribute BoardSaveRequestDto dto){
        Long newIdx = boardService.save( dto );

        boolean isFound = boardService.existsById(newIdx);
        if( isFound ){
            return "<script>alert('글쓰기 성공'); location.href='/board/listForm';</script>";
        }else{
            return "<script>alert('글쓰기 실패'); history.back();</script>";
        }
    }

    @GetMapping("/contentForm")
    public String contentForm(@RequestParam Long boardIdx){
        return "contentForm";
    }
}