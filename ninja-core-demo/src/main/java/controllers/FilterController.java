/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;


import javax.servlet.http.Cookie;

import ninja.Context;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.SecureFilter;

import com.google.inject.Singleton;

import filters.LoggerFilter;
import filters.TeaPotFilter;

@Singleton
public class FilterController {

	/**
	 * Not yet finished.
	 * 
	 * Simply demonstrates how controllers can be annotated and filtered using
	 * the FilterWith annotation:
	 * 
	 * @param context
	 */
	@FilterWith(SecureFilter.class)
	public Result filter(Context context) {

		context.getHttpServletResponse().addCookie(
				new Cookie("myname", "myvalue"));

		// System.out.println("cookies: " +
		// context.getHttpServletRequest().getCookies());

		if (context.getHttpServletRequest().getCookies() != null) {
			for (int i = 0; i < context.getHttpServletRequest().getCookies().length; i++) {
				System.out.println("cookie: "
						+ context.getHttpServletRequest().getCookies()[i]
								.getName());

			}
		}

		return Results.html();

	}
	/**
	 * Really cool. We are using two filters on the method.
	 * 
	 * Filters are executed sequentially. First the LoggerFilter then the
	 * TeaPotFilter.
	 * 
	 * The TeaPotFilter changes completely the output and the status.
	 * 
	 * @param context
	 */
	@FilterWith({
		LoggerFilter.class, 
		TeaPotFilter.class})
	public Result teapot(Context context) {
		
		//this will never be executed. Have a look at the TeaPotFilter.class!
		return Results.html();
		
	}

}
