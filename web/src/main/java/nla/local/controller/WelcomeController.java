/*
 * Copyright (C) 2014 GHX, Inc.
 *  Louisville, Colorado, USA.
 *  All rights reserved.
 *
 *  Warning: Unauthorized reproduction or distribution of this program, or
 *  any portion of it, may result in severe civil and criminal penalties,
 *  and will be prosecuted to the maximum extent possible under the law.
 *
 *  Created on 023 23.05.2014
 */
package nla.local.controller;

import nla.local.services.impl.CatalogServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class WelcomeController {

    @Autowired
    private CatalogServiceImp catalogServiceImpl;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String welcomePage() {
        return "welcome";
    }


}


