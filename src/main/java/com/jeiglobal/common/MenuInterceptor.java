package com.jeiglobal.common;

import java.io.*;
import java.text.*;
import java.util.*;

import javax.servlet.http.*;

import lombok.extern.slf4j.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.web.context.*;
import org.springframework.util.*;
import org.springframework.web.servlet.handler.*;

import com.jeiglobal.domain.auth.*;
import com.jeiglobal.domain.menu.*;
import com.jeiglobal.service.menu.*;
import com.jeiglobal.utils.*;
/**
 * 
 * 클래스명 : MenuIntercepter.java
 *
 * 작성일 : 2015. 9. 7.
 *
 * 작성자 : 전승엽(IT지원팀)
 * 
 * 컨트롤러에 가기 전 메뉴에 관련된 정보를 처리하는 인터셉터
 * 
 * 1. Authentication에 저장 된 사용자 정보 로드
 * 2. 현재 사용자가 요청한 URI정보 로드
 * 3. 사용자 정보로 메뉴 리스트 가져오기
 * 3-1. 메뉴 리스트가 null 이면 false 리턴
 * 3-2. 메뉴 리스트가 null이 아니면 사용자가 요청한 URI와 매칭이 되는 메뉴 정보 추출하고 depth가 1인 메뉴들을 따로 리스트로 만듬(헤더메뉴)
 * 4. 3-2에서 만든 헤더메뉴 정렬
 * 5. 사용자가 요청한 URI와 매칭이 되는 메뉴 코드가 비어 있지 않거나 허용되는 URL인 경우 출력하기 좋게 컬렉션에 담는다.
 * 5-1. 메뉴코드가 없거나 허용되는 URL이 아닌 경우 올바르지 않은 URL이라는 알럿창을 출력 후 Back
 */
@Slf4j
public class MenuInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private MessageSourceAccessor msa;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSessionSecurityContextRepository hsscr = new HttpSessionSecurityContextRepository();
		HttpRequestResponseHolder hrrh = new HttpRequestResponseHolder(request, response);
		SecurityContext context = hsscr.loadContext(hrrh);
		Authentication authentication = context.getAuthentication();
		LoginInfo loginInfo = (LoginInfo) authentication.getPrincipal();
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String currentUrl = request.getRequestURI();
		List<GlobalMenu> menuList = menuService.menuList(0,loginInfo.getJisaCD(),loginInfo.getUserType(),loginInfo.getUserLevel(),"1", loginInfo.getUserId());
		List<GlobalMenu> headerMenuList = new ArrayList<GlobalMenu>();
		List<List<GlobalMenu>> menus = new ArrayList<List<GlobalMenu>>();
		if(menuList == null){
			return false;
		}else{
			String menuCode = "";
			String menuFirstCode = "";
			String menuTwoCode = "";
			String menuThreeCode = "";
			String menuFourCode = "";
			
			AntPathMatcher ant = new AntPathMatcher();
			
			if("ROOT".equals(menuList.get(0).getMMenuName())){
				menuList.remove(0);
			}
			for (GlobalMenu globalMenu : menuList) {
				if(ant.match(globalMenu.getMAntPattern(),currentUrl)){
					menuCode = globalMenu.getMMenuCode();
				}
				if(globalMenu.getMMenuCode().length() == 1){
					headerMenuList.add(globalMenu);
				}
			}
			
			//헤더 메뉴 정렬
			Collections.sort(headerMenuList, new Comparator<GlobalMenu>() {
				@Override
				public int compare(GlobalMenu arg0, GlobalMenu arg1) {
					return Integer.parseInt(arg0.getMMenuCode()) < Integer.parseInt(arg1.getMMenuCode()) ? -1 : 
						Integer.parseInt(arg0.getMMenuCode()) > Integer.parseInt(arg1.getMMenuCode()) ? 1:0;
				}
			});
			
			int menuCodeCnt = menuCode.length();
			
			if(!menuCode.isEmpty() || "/ma".equalsIgnoreCase(currentUrl)){
				if(menuCodeCnt == 1){
					menuFirstCode = menuCode.substring(0,1);
				}else if(menuCodeCnt == 3){
					menuFirstCode = menuCode.substring(0,1);
					menuTwoCode = menuCode.substring(0,3);
				}else if(menuCodeCnt == 5){
					menuFirstCode = menuCode.substring(0,1);
					menuTwoCode = menuCode.substring(0,3);
					menuThreeCode =  menuCode.substring(0,5);
				}else if(menuCodeCnt == 7){
					menuFirstCode = menuCode.substring(0,1);
					menuTwoCode = menuCode.substring(0,3);
					menuThreeCode =  menuCode.substring(0,5);
					menuFourCode =  menuCode.substring(0,7);
				}	
				
				for (int i = 0; i < headerMenuList.size(); i++) {
					List<GlobalMenu> tempMenuList = new ArrayList<GlobalMenu>();
					tempMenuList.add(headerMenuList.get(i));
					for (GlobalMenu menu : menuList) {
						if(headerMenuList.get(i).getMIdx().longValue() == menu.getMParentIdx().longValue()){
							tempMenuList.add(menu);
						}
					}
					menus.add(tempMenuList);
				}
			}else{
				log.debug("Invalid Url : {}", currentUrl);
				
				String msg = msa.getMessage("error.menuinterceptor.invalidurl");
				PrintWriter writer = response.getWriter();
				response.setContentType("text/html;charset=UTF-8");
				String scriptMessage = "<script language='javascript'>alert('";
				scriptMessage += msg;
				scriptMessage += "');";
				scriptMessage += "history.back();</script>";
				writer.write(scriptMessage);
				return false;
			}
			request.setAttribute("menuMap",menus);
			request.setAttribute("headerMenuList",headerMenuList);
			request.setAttribute("menuCode",menuCode);
			request.setAttribute("menuFirstCode",menuFirstCode);
			request.setAttribute("menuTwoCode",menuTwoCode);
			request.setAttribute("menuThreeCode",menuThreeCode);
			request.setAttribute("menuFourCode",menuFourCode);
			request.setAttribute("currentUrl",currentUrl);
			request.setAttribute("nowDate",sdf.format(cal.getTime()));
			return true;
		}
	}
	
}
