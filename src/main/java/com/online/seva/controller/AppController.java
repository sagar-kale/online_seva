package com.online.seva.controller;

import com.online.seva.domain.Role;
import com.online.seva.domain.User;
import com.online.seva.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AppController {
    private static final String TEMPLATE_PREFIX = "views/";
    private static final String ADMIN_PAGE = "admin";
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);
    @Autowired
    private SessionService sessionService;

    @RequestMapping("/")
    String home(ModelMap modal) {
        modal.addAttribute("title", "online-seva");
        return "index";

    }

    @RequestMapping("/pagerouting/{page}")
    String partialHandler(@PathVariable("page") final String page, HttpServletRequest request, Model model) {
        boolean isAdmin = false;
        String view = TEMPLATE_PREFIX + page.trim();
        String forbidden = TEMPLATE_PREFIX + "forbidden";
        if (page.equalsIgnoreCase(ADMIN_PAGE)) {
            User loggedUser = sessionService.getLoggedUser(request);
            if (null == loggedUser) {
                model.addAttribute("logged", false);
                return forbidden;
            }
            logger.info("logged User ::: " + loggedUser);
            for (Role role : loggedUser.getRoles()) {
                logger.info("User Role::" + role.getRole());
                if (role.getRole().equalsIgnoreCase("admin"))
                    isAdmin = true;
            }

            if (isAdmin)
                return view;
            logger.info("not logged");
            model.addAttribute("logged", true);
            model.addAttribute("name", loggedUser.getName());
            return forbidden;
        }
        return view;

    }
}
