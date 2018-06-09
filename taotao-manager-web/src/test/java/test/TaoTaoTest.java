package test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

public class TaoTaoTest {

	private ApplicationContext applicationContext;

	@Test
	public void testPageHelper()
	{
		//创建Spring容器 从spring容器中获取容器对象
		
		applicationContext = 
				new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		
		TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
		//执行查询
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(2, 10);
		
		List<TbItem> list = itemMapper.selectByExample(example);
		for(TbItem tb:list)
		{
			String title = tb.getTitle();
			System.out.println(title);
		}
		
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		System.out.println("共有商品信息："+pageInfo.getTotal());
		
	
	}
	

}
