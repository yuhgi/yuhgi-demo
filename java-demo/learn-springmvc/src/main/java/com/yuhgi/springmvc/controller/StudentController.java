package com.yuhgi.springmvc.controller;

import com.alibaba.fastjson.JSONObject;
import com.yuhgi.springmvc.model.Student;
import com.yuhgi.springmvc.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @RequestMapping(value="/list",method = {RequestMethod.POST})
    @ResponseBody
    public Object queryList(@RequestBody JSONObject jsonObject){
        ArrayList<com.yuhgi.springmvc.model.Student> allList = studentService.getList();
        ArrayList<Student> studentArrayList = new ArrayList<Student>();
        int pageSize = jsonObject.getInteger("pageSize");
        int pageIndex = jsonObject.getInteger("pageIndex");
        for(int i=pageSize*pageIndex;i<pageSize*(pageIndex+1);i++){
            studentArrayList.add(allList.get(i));
        }

        return studentArrayList;
    }

    @RequestMapping(value="/list",method = {RequestMethod.GET})
    public Object showList(){
        return "student/list";
    }

    @RequestMapping(value="detail/{id}")
    public String showStudentDetail(@PathVariable String id, Model model){
        Student s = studentService.findStudent(id);
        if(s != null){
            model.addAttribute("id",s.getId());
            model.addAttribute("name",s.getName());
            model.addAttribute("age",s.getAge());
            model.addAttribute("subject",s.getSubject());
        }else{
            model.addAttribute("id","不存在");
        }

        return "student/detail";
    }

    @RequestMapping(value="/add",method=RequestMethod.GET)
    public String add(){

        return "student/add";
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public String addStudent(@Valid Student student, BindingResult bindingResult,
                             @RequestParam(value="image",required=false)MultipartFile file){
        if(bindingResult.hasErrors()){
            return "student/add";
        }
        if(!file.isEmpty()){
            System.out.println("照片上传成功");
        }
        this.studentService.addStudent(student);

        return "redirect:list";
    }

    @RequestMapping(value="/edit")
    public String editStudent(){
        return "student/edit";
    }

    @RequestMapping(value="/del")
    public String delStudent(@RequestParam("id") String id,Map<String,Object> model){
        String result;
        Student s = studentService.delStudent(id);
        if(s!=null){
            result = s.getId();
        }else {
            result = "失败";
        }
        model.put("result",result);
        return "student/del";
    }
}
