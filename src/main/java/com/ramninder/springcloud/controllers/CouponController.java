package com.ramninder.springcloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ramninder.springcloud.model.Coupon;
import com.ramninder.springcloud.repos.CouponRepo;

@Controller
public class CouponController {

	@Autowired
	private CouponRepo repo;

	

	@GetMapping("/showCreateCoupon")
	public String showCreateCoupon() {

		return "createCoupon";
	}

	@PostMapping("/saveCoupon")
	public String save(Coupon c) {

		repo.save(c);
		return "createResponse";

	}

	@GetMapping("/showGetCoupon")
	public String showgetCoupon() {

		return "getCoupon";
	}

	@PostMapping("/getCoupon")
	public ModelAndView getCoupon(String code) {
		ModelAndView mav = new ModelAndView("couponDetails");
		mav.addObject(repo.findByCode(code));
		return mav;

	}

}
