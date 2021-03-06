package com.online.seva.controller;

import com.online.seva.domain.Job;
import com.online.seva.domain.User;
import com.online.seva.service.JobService;
import com.online.seva.service.SessionService;
import com.online.seva.util.FormatDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class AppController {
    private static final String TEMPLATE_PREFIX = "views/";
    private static final String ADMIN_PAGE = "admin";
    private static final String forbidden = "/forbidden";
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);
    @Autowired
    private SessionService sessionService;
    @Autowired
    private JobService jobService;
    @Autowired
    private FormatDate formatDate;

    @RequestMapping("/")
    String home(ModelMap modal) {
        modal.addAttribute("title", "online-seva");
        return "index";

    }

    @RequestMapping("/login")
    String login(ModelMap modal) {
        modal.addAttribute("title", "online-seva");
        return "user_home";

    }

    @RequestMapping("/jobs/download/poster/{id}")
    String downloadPoster(@PathVariable("id") String id, ModelMap modal, HttpServletRequest request,
                          RedirectAttributes redirectAttributes) {
        User loggedUser = sessionService.getLoggedUser(request);
        if (null == loggedUser) {
            redirectAttributes.addFlashAttribute("routed", true);
            redirectAttributes.addFlashAttribute("logged", false);
            return "redirect:" + forbidden;
        }

        logger.info("Id::::: " + id);
        Optional<Job> byID = jobService.findByID(id);
        Job job = byID.get();
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        if (job.getTitle().contains(" ")) {

            for (String s : job.getTitle().split(" ")) {
                if (i < 3)
                    stringBuilder.append(s.concat(" "));
                i++;
            }
        } else {
            stringBuilder.append(job.getTitle());
        }

        modal.addAttribute("title", stringBuilder.toString());
        modal.addAttribute("post", "" /*job.getJobSubDetails().getPostName()*/);
        /*modal.addAttribute("qualification", ""*//*job.getJobSubDetails().getEducationalQualifiction()*//*);*/
        /*modal.addAttribute("salary", job.getJobSubDetails().getSalaryScale());*/
        modal.addAttribute("age", job.getJobSubDetails().getAgeLimit());
        modal.addAttribute("lastDate", formatDate.getFormatDate(job.getLastDate()));
        modal.addAttribute("totalPost", job.getTotalPosts());
        modal.addAttribute("center", loggedUser.getCenterName());
        modal.addAttribute("contact", loggedUser.getPhone());
        // image path src="../../../images/banner3.jpg"
        return "pdf/jobPDF";

    }

    @RequestMapping("/chat")
    public String chat(ModelMap modal, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        User loggedUser = sessionService.getLoggedUser(request);
        if (null == loggedUser) {
            redirectAttributes.addFlashAttribute("routed", true);
            redirectAttributes.addFlashAttribute("logged", false);
            return "redirect:" + forbidden;
        }
        modal.addAttribute("name", loggedUser.getName());
        return "views/chat";

    }

    @RequestMapping("/pagerouting/{page}")
    String partialHandler(@PathVariable("page") final String page, HttpServletRequest request,
                          RedirectAttributes redirectAttributes, HttpServletResponse response, Model model) {
        logger.error("page::" + page);
        String view = TEMPLATE_PREFIX + page.trim();
        if (!page.equalsIgnoreCase("login")) {
            User loggedUser = sessionService.getLoggedUser(request);
            if (null == loggedUser) {
                redirectAttributes.addFlashAttribute("routed", true);
                redirectAttributes.addFlashAttribute("logged", false);
                return "redirect:" + forbidden;
            }
            logger.info("logged User ::: " + loggedUser);

            if (!page.equalsIgnoreCase(ADMIN_PAGE)) {
                if (page.equals("header")) {
                    model.addAttribute("loggedUser", loggedUser);
                }
                return view;
            }

            if (sessionService.isAdmin(loggedUser) || sessionService.isContentAdmin(loggedUser)) {
                model.addAttribute("loggedUser", loggedUser);
                return view;
            }
            logger.info("not admin");
            redirectAttributes.addFlashAttribute("logged", true);
            redirectAttributes.addFlashAttribute("name", loggedUser.getName());
            return "redirect:/" + "accessDenied";
        }
        /*
         * User loggedUser = sessionService.getLoggedUser(request); if (null ==
         * loggedUser) return view;
         */

        return view;
    }
}
