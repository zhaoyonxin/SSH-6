package junit.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.entity.User;
import com.example.service.UserService;
import com.example.utils.Page;

public class MainTest {
	private ApplicationContext context = null;
	
	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Test
	public void testSpring() {
		UserService bean = context.getBean(UserService.class);
		System.out.println(bean.getClass().getName());
		User user = new User("thinkervsruler", "123", "lghuntfor@sina.com", "1231564987");
		bean.save(user);
	}
	
	@Test
	public void testPage() {
		UserService service = context.getBean(UserService.class);
		Page<User> page = service.getPage(1, 2, null, "username", "thinkervsruler");
		for(User user : page.getContent()) {
			System.out.println(user);
		}
	}
	
	
	@Test
	public void testLike() {
		UserService service = context.getBean(UserService.class);
		List<User> list = service.like("username", "thinker");
		for(User user :list) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testGet() {
		UserService service = context.getBean(UserService.class);
		String username = service.getPropertyValueById(3, "username");
		System.out.println(username);
	}
	
}
