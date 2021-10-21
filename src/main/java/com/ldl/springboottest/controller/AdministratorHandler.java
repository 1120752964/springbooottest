package com.ldl.springboottest.controller;


import com.ldl.springboottest.entity.Administrator;
import com.ldl.springboottest.entity.User;
import com.ldl.springboottest.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/administrator")
public class AdministratorHandler {

    @Autowired
    private AdministratorRepository administratorRepository;

    @PostMapping("/login")
    public Administrator login(@RequestBody Administrator administrator){

        //如果数据库中没有改管理员时,aid为0
        Administrator administratortotal = new Administrator();
        Administrator administratorset = new Administrator();
        String string = DigestUtils.md5DigestAsHex(administrator.getApassword().getBytes());
        administratortotal.setAid(0);
        List<Administrator> administrators = administratorRepository.findByanumber(administrator.getAnumber());

        for(int i=0;i<administrators.size();i++){
            Administrator administrator1 = administrators.get(i);
            if (string.equals(administrator1.getApassword())){
                administratortotal = administrator1;
                //在登录时将对应的administrator的状态位置1
                administratorset = administrator1;
                administratorset.setAstatus(1);
                administratorRepository.save(administratorset);
            }
        }
            return administratortotal;
    }

    //改密码专用
    @PostMapping("/findAl")
    public List<Administrator> findAl(){
        List<Administrator> administrators =  administratorRepository.findAll();
        for (int i = 0; i < administrators.size(); i++) {
            Administrator administrator = administrators.get(i);
            administrator.setApassword(DigestUtils.md5DigestAsHex(administrator.getApassword().getBytes()));
            administratorRepository.save(administrator);
        }
        return administratorRepository.findAll();
    }

    @GetMapping("/findAll/{page}/{size}")
    public Page<Administrator> findAll(@PathVariable("page")Integer page, @PathVariable("size")Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        return administratorRepository.findAll(pageable);
    }

    @PostMapping("/save")
    public String save(@RequestBody Administrator administrator){
        Administrator administrator1 = administratorRepository.save(administrator);
        if(administrator1!=null){
            return "success";
        }else {
            return "error";
        }
    }

    @GetMapping("/findAdministratorByaid/{aid}")
    public Administrator findById(@PathVariable("aid")Integer aid){
        System.out.println(aid);
        System.out.println(administratorRepository.findById(aid).get());
        return administratorRepository.findById(aid).get();
    }

    @PutMapping("/update")
    public String update(@RequestBody Administrator administrator){
        Administrator administrator1 = administratorRepository.save(administrator);
        if(administrator1!=null){
            return "success";
        }else {
            return "error";
        }
    }
    @DeleteMapping("/deleteUserById/{id}")
    public void delete(@PathVariable("id")Integer id){
        administratorRepository.deleteById(id);
    }


    //在home.vue中获取对应的astatus=1的Administrator
    @GetMapping("/getAdministrator")
    public Administrator getAdministrator(){
        List<Administrator> administrators = administratorRepository.findAll();
        Administrator administrator = new Administrator();
        administrator.setAid(0);
        for (int i = 0; i < administrators.size(); i++) {

            if (administrators.get(i).getAstatus()==1){
                administrator = administrators.get(i);
            }
        }
        return administrator;
    }

//    @PutMapping("/setAdministrator/{id}")
//    public void setAdministrator(@PathVariable("id")Integer id){
//        Administrator administrator = administratorRepository.findById(id).get();
//        administrator.setAstatus(1);
//        administratorRepository.save(administrator);
//    }

    @PutMapping("/clearAdministrator")
    public void clearAdministrator(){
        List<Administrator> administrators = administratorRepository.findAll();
        for (int i = 0; i < administrators.size(); i++) {
            Administrator administrator = administrators.get(i);

            administrator.setAstatus(0);
            administratorRepository.save(administrator);
        }

    }

}
