package com.example.saenaljigi.controller;

import com.example.saenaljigi.service.NoticeDetailService;
import com.example.saenaljigi.service.NoticeService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;
    private final NoticeDetailService noticeDetailService;

    @PostMapping
    public ResponseEntity<String> getNoticeList() {
        try {
            String jsonResponse = noticeService.noticeCrawl();
            return ResponseEntity.ok(jsonResponse);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"error\": \"공지사항 목록 요청 중 에러 발생: " + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/{seq}")
    public ResponseEntity<Object> getNoticeDetail(@PathVariable String seq) {
        try {
            ObjectNode noticeDetail = noticeDetailService.noticeDetailCrawl(seq);

            return ResponseEntity.ok(noticeDetail);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("상세 페이지 요청 중 에러 발생: " + e.getMessage());
        }
    }
}
