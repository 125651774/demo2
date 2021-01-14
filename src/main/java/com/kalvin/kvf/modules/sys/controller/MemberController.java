package com.kalvin.kvf.modules.sys.controller;

import cn.hutool.core.bean.BeanUtil;
import com.kalvin.kvf.common.constant.SysConstant;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.common.utils.CryptionKit;
import com.kalvin.kvf.modules.sys.dto.UserEditDTO;
import com.kalvin.kvf.modules.sys.dto.UserRoleGroupDTO;
import com.kalvin.kvf.modules.sys.entity.Dept;
import com.kalvin.kvf.modules.sys.entity.User;
import com.kalvin.kvf.modules.sys.service.IDeptService;
import com.kalvin.kvf.modules.sys.service.IUserRoleService;
import com.kalvin.kvf.modules.sys.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.kalvin.kvf.modules.sys.controller
 * @Project: kvf
 * @Author TaoLiang
 * @Date 2021/1/9 15:18
 * @Description
 **/
@RestController
@RequestMapping("member")
public class MemberController extends BaseController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IDeptService deptService;

    @Autowired
    private IUserRoleService userRoleService;

    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("member/products");
    }
    @GetMapping("login")
    public ModelAndView login() {
        return new ModelAndView("member/login");
    }
    @GetMapping("products")
    public ModelAndView products() {
        return new ModelAndView("member/products");
    }
    @GetMapping(value = "add")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("member/user_edit");
        UserEditDTO userEditDTO = new UserEditDTO();
        UserRoleGroupDTO userRoleGroupDTO = new UserRoleGroupDTO();
        if (id != null) {
            User user = userService.getById(id);
            Dept dept = deptService.getById(user.getDeptId());
            userRoleGroupDTO = userRoleService.getUserRoleGroupDTOByUserId(id);
            BeanUtil.copyProperties(user, userEditDTO);
            userEditDTO.setDeptName(dept == null ? "" : dept.getName());
        }
        userEditDTO.setUserRole(userRoleGroupDTO);
        mv.addObject("editInfo", userEditDTO);
        return mv;
    }
    @Transactional
    @PostMapping(value = "regist")
    public R regist(User user) {
        //查询用户list
        List<User> l_user = userService.search(user.getUsername());
        //System.out.println(l_user.isEmpty());
        //判断用户是否存在
        if(l_user.isEmpty()){
            //设置默认部门
            user.setDeptId((long)2);
            // 生成用户初始密码并加密
            user.setPassword(CryptionKit.genUserPwd());
            //保存用户信息
            userService.saveOrUpdate(user);
            //设置默认角色为5（会员）
            List<Long> roleIds = new ArrayList<Long>();
            roleIds.add(5L);
            //保存用户权限
            userRoleService.saveOrUpdateBatchUserRole(roleIds, user.getId());
            return R.ok();
        }
        return R.fail("用户已存在！");
    }
}
