package com.jw.mycaferecipe.service.menu;

import com.jw.mycaferecipe.entity.MenuDTO;
import com.jw.mycaferecipe.repository.menu.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    /**
     * 메뉴등록 비즈니스로직
     */
    public void enroll(MenuDTO menuDTO, MultipartFile file) throws Exception{

        if(file.isEmpty()) {}
        else {
            //파일저장경로 설정
            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

            //파일업로드 시 랜덤아이디생성
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();
            //생성된 아이디로 설정한 경로에 파일저장
            File saveFile = new File(projectPath, fileName);
            file.transferTo(saveFile);

            //DB에 파일이름과 경로저장
            menuDTO.setFilename(fileName);
            menuDTO.setFilepath("/files/" + fileName);
        }

        menuRepository.save(menuDTO);
    }

    /**
     * 메뉴리스트 로직
     */
    public List<MenuDTO> menuList() {
        List<MenuDTO> menuList = menuRepository.findAll();
        return menuList;
    }

    /**
     * 메뉴상세 로직
     */
    public Optional<MenuDTO> menuDetail(@RequestParam("num") Long num) {
        Optional<MenuDTO> menuDetail = menuRepository.findById(num);
        return menuDetail;
    }

    /**
     * 메뉴상세 로직
     */
    public void menuDelete(@RequestParam("num") Long num) {
        menuRepository.deleteById(num);
    }
}
