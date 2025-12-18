package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import jp.co.sss.crud.bean.LoginResultBean;
import jp.co.sss.crud.form.LoginForm;
import jp.co.sss.crud.service.LoginService;

@Controller
public class IndexController {

	@Autowired
	LoginService loginService;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index(@ModelAttribute LoginForm loginForm) {
		return "index";
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(@Validated @ModelAttribute LoginForm loginForm,BindingResult result, Model model, HttpSession session) {

		String path = "index";

		//TODO LoginServiceが完成後にコメントを外す
		if (result.hasErrors()) {
					return path;
				}
			
		LoginResultBean loginResultBean = loginService.execute(loginForm);

		if (loginResultBean.isLogin()) {
			session.setAttribute("loginUser", loginResultBean.getLoginUser());
			 session.setAttribute("authority", loginResultBean.getLoginUser().getAuthority());
			path = "redirect:/list";
		} else {
			
			model.addAttribute("errMessage", "社員ID、またはパスワードが間違っています。");
		}

		return path;
	}


	

	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {

		session.invalidate();
		return "redirect:/";
	}

}
