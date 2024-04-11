<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ page
trimDirectiveWhitespaces="true" %> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>자판기 상품 추가</title>
    <style>
      /* 폰트 */
      @font-face {
        font-family: "Pretendard-Regular";
        src: url("https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff") format("woff");
        font-weight: 400;
        font-style: normal;
      }

      * {
        font-family: "Pretendard-Regular";
      }

      .wrap {
        margin: 100px auto;
        width: 400px;
      }

      div.container {
        background-color: rgb(187, 41, 41);
        padding: 10px;
        border: 1px solid white;
        width: 500px;
        height: 300px;
      }

      /* 자판기 타이틀 */
      .title-box {
        /* background-color: rgb(255, 255, 255); */
        width: 100%;
        height: 30px;
        display: flex;
        flex-wrap: wrap;
        align-content: center;
        justify-content: center;
      }

      .title {
        color: rgb(187, 41, 41);
        margin: 0px;
        text-shadow: -1px 0px white, 0px 1px white, 1px 0px white, 0px -1px white;
        font-size: 24px;
      }

      .addButton {
        margin-top: 10px;
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
      }

      .addButton button {
        margin: 0 5px;
      }

      label {
        margin: 2px;
      }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!--  부트스트랩 CDN 링크 -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
      crossorigin="anonymous" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" />
  </head>
  <body style="background-color: black">
    <div class="wrap">
      <div class="container">
        <div class="title-box">
          <h2 class="title fw-bold">자판기 상품 추가</h2>
        </div>

        <form action="/addProduct" method="post">
          <!-- 상품명 -->
          <div class="form-group">
            <label for="inputName" class="text-white">상품명:</label>
            <input type="text" class="form-control" id="inputName" name="inputName" required />
          </div>

          <!-- 가격 -->
          <div class="form-group">
            <label for="inputPrice" class="text-white">가격:</label>
            <input type="number" class="form-control" id="inputPrice" name="inputPrice" required />
          </div>

          <!-- 유통기한 -->
          <div class="form-group">
            <label for="inputLimitDate" class="text-white">유통기한:</label>
            <input type="text" class="form-control datepicker" id="inputLimitDate" name="inputLimitDate" required />
          </div>

          <!-- 상품 추가 -->
          <div class="addButton">
            <button type="submit" class="btn btn-dark">상품 추가 완료!</button>
            <button type="button" onclick="history.back();" class="btn btn-light">돌아가기</button>
          </div>
        </form>
      </div>
    </div>

    <!--  부트스트랩 CDN 링크 -->
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
      crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/locales/bootstrap-datepicker.kr.min.js"></script>
    <script>
      function productAdded() {
        alert("상품을 추가하였습니다!");
      }

      $(document).ready(function () {
        $(".datepicker").datepicker({
          format: "yyyy-mm-dd", // 날짜 형식 설정
          language: "kr", // 언어 설정 (한국어)
          autoclose: true, // 선택 후 자동으로 닫힘
        });
      });
    </script>
  </body>
</html>
