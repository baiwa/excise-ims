//package th.co.baiwa.framework.admin.service;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import th.co.baiwa.framework.admin.dao.UserDao;
//import th.co.baiwa.framework.admin.persistence.entity.User;
//import th.co.baiwa.framework.common.bean.DataTableAjax;
//import th.co.baiwa.framework.common.bean.EditDataTable;
//import th.co.baiwa.framework.common.constant.CommonConstants.EditDataTableAction;
//
//@Service("userService")
//public class UserService implements UserService {
//
//	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
//
//	
//	@Autowired
//	private UserDao userDao;
//	
//	@Override
//	public DataTableAjax<User> queryUserList(Integer start, Integer length) {
//		
//        DataTableAjax<User> dataTableAjax = new DataTableAjax<>();
//		
//		Long count = userDao.queryCountUserList();
//		List<User> data = userDao.queryUserList(start,length);
//		dataTableAjax.setRecordsTotal(count.intValue());
//		dataTableAjax.setRecordsFiltered(count.intValue());
//		dataTableAjax.setData(data);
//		
//		return dataTableAjax;
//	}
//
//	@Override
//	public void userAction(EditDataTable<User> editDataTable) {
//		
//		logger.info("userAction");
//		
//		if (EditDataTableAction.CREATE.equals(editDataTable.getAction())) {
//			for (User user : editDataTable.getData()) {
//				user.setAccountNonExpired("Y");
//				user.setAccountNonLocked("Y");
//				user.setCredentialsNonExpired("Y");
//				userDao.persist(user);
//			}
//		} else if (EditDataTableAction.EDIT.equals(editDataTable.getAction())) {
//			User oldUser = null;
//			for (User user : editDataTable.getData()) {
//				oldUser = userDao.findOne(user.getUserId());
//				oldUser.setUsername(user.getUsername());
//				oldUser.setEnabled(user.getEnabled());
//				userDao.merge(oldUser);
//			}
//		} else if (EditDataTableAction.REMOVE.equals(editDataTable.getAction())) {
//			for (User user : editDataTable.getData()) {
//				userDao.delete(user);
//			}
//		}
//		
//	}
//	
//}
