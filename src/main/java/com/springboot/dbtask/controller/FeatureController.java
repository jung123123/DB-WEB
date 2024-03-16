package com.springboot.dbtask.controller;

import com.springboot.dbtask.data.dto.FeatureDTO.FeatureDto;
import com.springboot.dbtask.data.dto.FeatureDTO.FeatureResponseDto;
import com.springboot.dbtask.data.repository.FeatureRepository;
import com.springboot.dbtask.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feature")
public class FeatureController {

    @Autowired
    FeatureService featureService;

    @Autowired
    FeatureRepository featureRepository;



//    @GetMapping("/{id}")    // http://localhost:8080/feature/{id}
//    public ResponseEntity<FeatureDto> getFeature(@PathVariable Long id) {
//        FeatureDto featureDto = featureService.getFeature(id);
//
//        return ResponseEntity.status(HttpStatus.OK).body(featureDto);   //HTTP 상태 코드 200(OK)를 나타내며, 클라이언트의 요청이 성공적으로 처리되었음을 의미
//    }


    @PostMapping()
    public ResponseEntity<FeatureResponseDto> createFeature(@RequestBody FeatureDto featureDto) {       //Feature 테이블에 값 추가 insert
        FeatureResponseDto featureResponseDto = featureService.saveFeature(featureDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(featureResponseDto);  //HTTP 상태 코드 201(Created)를 나타내며, 클라이언트의 요청이 성공적으로 처리되고 새로운 리소스가 생성되었음을 의미
    }


    @PostMapping("/{name}")
    public ResponseEntity<FeatureResponseDto> createFeature(@PathVariable String name) {       //Feature 테이블에 값 추가 insert

        FeatureDto featureDto = new FeatureDto();
        featureDto.setFeatureName(name);
        FeatureResponseDto featureResponseDto = featureService.saveFeature(featureDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(featureResponseDto);  //HTTP 상태 코드 201(Created)를 나타내며, 클라이언트의 요청이 성공적으로 처리되고 새로운 리소스가 생성되었음을 의미
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FeatureDto>> getAllFeature(){                //Feature 테이블 리스트 보기 select
        List<FeatureDto> listsDto =  featureService.getAllFeature();
        return ResponseEntity.status(HttpStatus.OK).body(listsDto);     //HTTP 상태 코드 200(OK)를 나타내며, 클라이언트의 요청이 성공적으로 처리되었음을 의미
    }



//    @PutMapping()
//    public ResponseEntity<FeatureResponseDto> updateFeatureName(            //Feature 테이블 수정 update
//            @RequestBody UpdateFeatureDto updateFeatureDto) throws Exception {
//        FeatureResponseDto featureResponseDto = featureService.updateFeatureName(
//                updateFeatureDto.getFeatureNumber(),
//                updateFeatureDto.getFeatureName());
//
//        return ResponseEntity.status(HttpStatus.OK).body(featureResponseDto);
//
//    }


    @PutMapping("/{id}/{name}")
    public ResponseEntity<FeatureResponseDto> updateFeatureName(            //Feature 테이블 수정 update
         @PathVariable Long id,@PathVariable String name) throws Exception {

        FeatureResponseDto featureResponseDto = featureService.updateFeatureName(id, name);

        return ResponseEntity.status(HttpStatus.OK).body(featureResponseDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFeature(@PathVariable Long id) throws Exception {       //Feature 테이블 삭제 delete
        featureService.deleteFeature(id);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }



    @DeleteMapping("/")
    public ResponseEntity<String> deleteAllFeature() throws Exception {       //Feature 테이블 삭제(전체) delete
        featureService.deleteAllFeature();
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }


}
