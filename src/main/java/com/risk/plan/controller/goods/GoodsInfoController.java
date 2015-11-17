package com.risk.plan.controller.goods;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.risk.plan.common.PageBean;
import com.risk.plan.entity.ENRelation;
import com.risk.plan.entity.EmerType;
import com.risk.plan.entity.Emergency;
import com.risk.plan.entity.Goods;
import com.risk.plan.entity.GoodsType;
import com.risk.plan.service.box.goods.GoodsService;
import com.risk.plan.service.box.goods.GoodsTypeService;
import com.risk.plan.util.Identities;

@Controller
public class GoodsInfoController {
	//service
	@Autowired
	GoodsTypeService goodsTypeService;
	@Autowired
	GoodsService goodsService;
	
	//httpservlet
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	
	/** 
	* @Title: gotoAddGoods 
	* @Description: 添加物资
	* @param modelmap
	* @return String    返回类型    
	*/
	@RequestMapping("/gotoAddGoods")
	public String gotoAddGoods(ModelMap modelmap){
		List<String> unitlist=new ArrayList<String>();
		unitlist.add("千克");
		unitlist.add("顶");
		unitlist.add("箱");
		unitlist.add("其它");
		modelmap.put("unitlist", unitlist);
		List<String> goodstypenames=goodsTypeService.findAllGoodsTypeName();
		String goodstypename=goodstypenames.get(0); 
		List<GoodsType> goodstypelist=goodsTypeService.findBygoodsTypename(goodstypename);
		modelmap.put("goodstypename", goodstypenames );
		modelmap.put("goodstypelist", goodstypelist );
		
		return "WebRoot/Goods/addGoods";
	}
	
	/** 
	* @Title: saveGoods 
	* @Description: 保存物资
	* @param modelmap
	* @param goods
	* @return String    返回类型    
	*/
	@RequestMapping("/saveGoods")
	public String saveGoods(ModelMap modelmap,Goods goods){
		goods.setGoodsid(Identities.uuid2());
		int resault=goodsService.insertSelective(goods);
		if(resault >0)
		return "forward:gotoAddGoods";
		else
			return "error/500";
	}
	
	/** 
	* @Title: GotoSearchGoods 
	* @Description: 查找页面
	* @param modelmap
	* @return String    返回类型    
	*/
	@RequestMapping("/GotoSearchGoods")
	public String GotoSearchGoods(ModelMap modelmap){
		//加载类型
		List<String> goodstypenames=goodsTypeService.findAllGoodsTypeName();
		String goodstypename=goodstypenames.get(0); 
		List<GoodsType> goodstypelist=goodsTypeService.findBygoodsTypename(goodstypename);
		modelmap.put("goodstypename", goodstypenames );
		modelmap.put("goodstypelist", goodstypelist );
		
		
		return "WebRoot/Goods/listGoods";
	}
	
	@RequestMapping("/SearchGoods")
	public String SearchGoods(ModelMap modelmap,String page,Goods goods,GoodsType goodsType){
		 StringBuffer url=new StringBuffer();
	     url.append(request.getContextPath()+"/SearchGoods?method=SearchGoods");
		
		//加载类型
		List<String> goodstypenames=goodsTypeService.findAllGoodsTypeName();
		String goodstypename=goodstypenames.get(0);
		if(goodsType.getGoodstypename()!= null)
		{
			goodstypename=goodsType.getGoodstypename();
		}
			
		List<GoodsType> goodstypelist=goodsTypeService.findBygoodsTypename(goodstypename);
		modelmap.put("goodstypename", goodstypenames );
		modelmap.put("goodstypelist", goodstypelist );
		
		//按条件查找
		   int current=1;
			if (page != null && !page.trim().equals("")) {
				current=Integer.parseInt(page);
			}
				int first=0;
				int pageSize=Integer.parseInt(String.valueOf(PageBean.pagecount));
				if(current!=0){
					first=(current-1)*pageSize;
				}
				int startNum = first;
				Map<String, Object> params=new HashMap<String, Object>();
				params.put("sortName", "goodsid");
		        params.put("pageSize", pageSize);
			    params.put("startNum", startNum);
			    String goodsname=goods.getGoodsname();
			    if(goodsname != null && !"".equals(goodsname)){
			    	params.put("goodsname", "%"+goodsname+"%");
			    	modelmap.put("goodsname", goodsname);
			    	url.append("&goodsname="+goodsname);
			    }
			    String goodsno=goods.getGoodsno();
			    if(goodsno != null && !"".equals(goodsno)){
			    	params.put("goodsno", "%"+goodsno+"%");
			    	modelmap.put("goodsno", goodsno);
			    	url.append("&goodsno="+goodsname);
			    }
			    String goodstypeid=goods.getGoodstypeid();
			    if(goodstypeid != null && !"".equals(goodstypeid)){
			    	params.put("goodstypeid", "%"+goodstypeid+"%");
			    	modelmap.put("goodstypeid", goodstypeid);
			    	url.append("&goodstypeid="+goodstypeid);
			    }
			    List<Goods> goodslist=goodsService.findByParams(params);
			    for (int i = 0; i < goodslist.size(); i++) {
			    	Goods g=goodslist.get(i);
			    	GoodsType goodstypeN=goodsTypeService.selectByPrimaryKey(g.getGoodstypeid());
			    	g.setGoodstype(goodstypeN);
			    	goodslist.set(i, g);
			    }
			    int num=goodsService.findByParamsCount(params);
				PageBean PageBean=new PageBean(current,pageSize,num,goodslist); 
				PageBean.setUrl(url.toString());
				modelmap.put("pageBean", PageBean);
				modelmap.put("Pagelist", PageBean.getList());
		
				return "WebRoot/Goods/listGoods";
	}
	/** 
	* @Title: EditGoods 
	* @Description: 编辑物资
	* @param modelmap
	* @param goodsid
	* @return String    返回类型    
	*/
	@RequestMapping("/EditGoods")
	public String EditGoods(ModelMap modelmap,String goodsid){
		List<String> unitlist=new ArrayList<String>();
		unitlist.add("千克");
		unitlist.add("顶");
		unitlist.add("箱");
		unitlist.add("其它");
		modelmap.put("unitlist", unitlist);
		List<String> goodstypenames=goodsTypeService.findAllGoodsTypeName();
		String goodstypename=goodstypenames.get(0); 
		List<GoodsType> goodstypelist=goodsTypeService.findBygoodsTypename(goodstypename);
		modelmap.put("goodstypename", goodstypenames );
		modelmap.put("goodstypelist", goodstypelist );
		
		//通过id查找物资
		
		Goods goods=goodsService.selectByPrimaryKey(goodsid);
		GoodsType goodstype=goodsTypeService.selectByPrimaryKey(goods.getGoodstypeid());
		modelmap.put("goods", goods);
		modelmap.put("goodstype", goodstype);
		
		return "WebRoot/Goods/editGoods";
	}

	/** 
	* @Title: UpdateGoods 
	* @Description: 更新物资
	* @param modelmap
	* @param goods
	* @param goodstypeid
	* @return String    返回类型    
	*/
	@RequestMapping("/UpdateGoods")
	public String UpdateGoods(ModelMap modelmap,Goods goods,String goodstypeid){
		int resault=goodsService.updateByPrimaryKeySelective(goods);
		GoodsType goodsType=goodsTypeService.selectByPrimaryKey(goodstypeid);
		modelmap.put("goodstypeid", goodstypeid);
		modelmap.put("goodstypename",goodsType.getGoodstypename());
		if(resault >0)
			return "forward:SearchGoods";
		else{
			modelmap.put("goodsid", goods.getGoodsid());
			return "forward:EditGoods";
		}
	}
	
	/** 
	* @Title: deletegoods 
	* @Description: 删除物资
	* @param modelmap
	* @return String    返回类型    
	*/
	@RequestMapping("/deletegoods")
	public String deletegoods(ModelMap modelmap,Goods goods){
		int result;
		result=goodsService.deleteByPrimaryKey(goods.getGoodsid());
		if(result >0)
			return "redirect:SearchGoods";
		else
			return "WebRoot/DisasterNode/addfailed";
	}
	
	@ResponseBody
	@RequestMapping("/findGrade")
	public void findGrade(String goodsTypeName) throws UnsupportedEncodingException{
		goodsTypeName=URLDecoder.decode(goodsTypeName, "UTF-8");
		String cSelect = "";
		try {
			List<GoodsType> goodstypelist=goodsTypeService.findBygoodsTypename(goodsTypeName);
			if (goodstypelist != null && goodstypelist.size() > 0) {
				for (int i = 0; i < goodstypelist.size(); i++) {
					GoodsType type = goodstypelist.get(i);
					cSelect += "<option value=" + type.getGoodstypeid()
							+ ">" + type.getGrade() + "</option>";
				}
			} else {
				cSelect += "<option value=\"0\">该区下无街道</option>";
			}			
		response.getWriter().write(cSelect);
		} catch (Exception e) {
		}
		
	}
}
