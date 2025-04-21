/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.itpk.pkapi_interface.Interface;

import com.itpk.pkapiclientsdk.model.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 * */
@RestController
@RequestMapping("/name")
public class interface_controller {

  @GetMapping("/get")
    public  String getName(@RequestParam String name)
  {
      System.out.println(name);
      return "name是"+name;
  }
    @PostMapping("/post")
    public  String getPostName(@RequestParam String name)
    {
        System.out.println(name);
        return "name是"+name;
    }
    @PostMapping("/user")
    public  String getPostJsonName(@RequestBody User user , HttpServletRequest request)
    {
        System.out.println(user.getName());
        return user.getName();
    }
}
