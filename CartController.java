package jp.co.internous.brown.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.brown.model.domain.TblCart;
import jp.co.internous.brown.model.domain.dto.CartDto;
import jp.co.internous.brown.model.form.CartForm;
import jp.co.internous.brown.model.mapper.TblCartMapper;
import jp.co.internous.brown.model.session.LoginSession;


@Controller
@RequestMapping("/brown/cart")
public class CartController {
	
	@Autowired
	private LoginSession loginSession;
	
	@Autowired
	private TblCartMapper tblCartMapper;
	
	private Gson gson = new Gson();
	
	//初期表示
	@RequestMapping("/")
	public String index(Model m) {
		
		//ユーザーIDを取得、失敗した場合仮ユーザーIDを取得
		boolean loginFlag = loginSession.getLoginFlag();
		int id = loginSession.getId();
		if(false == loginFlag) {
			id = loginSession.getTempId();
		}
		
		//カート内容表示
		List<CartDto> cartList = tblCartMapper.findByUserId(id);
		
		//loginSessionも画面に表示
		m.addAttribute("cartList",cartList);
		m.addAttribute("loginSession", loginSession);
		
		return "cart";
	}
	
	
	//カート情報削除
	//true：削除成功　false:削除失敗（削除失敗した際には特に何も起こらない）
	@RequestMapping("/delete")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public boolean deleteCart(@RequestBody String checkedIdList) {
		int result = 0;
		
		Map<String, List<Integer>> map = gson.fromJson(checkedIdList, Map.class);
		List<Integer> checkedIds = map.get("checkedIdList");
		
		result = tblCartMapper.deleteCartById(checkedIds);
		
		return result > 0;
	}
	
	
	//カートに追加
	@RequestMapping("/add")
	@ResponseBody
	public boolean addCart(@RequestBody CartForm form) {
		
		//ユーザーIDを取得
		boolean loginFlag = loginSession.getLoginFlag();
		int id = loginSession.getId();
		if(false == loginFlag) {
			id = loginSession.getTempId();
		}
		form.setId(id);
		
		//同じidかつ同じproductIdであれば、更新する
		List<TblCart> tblCart = tblCartMapper.findByUserIdAndProductId(id,form.getProductId());
		int result = 0;
		if(tblCart.size() > 0) {
			result = tblCartMapper.tblCartUpdate(form);
		} else {
			result = tblCartMapper.tblCartInsert(form);
		}
		
		return result > 0;
		
	}
	
}
