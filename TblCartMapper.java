package jp.co.internous.brown.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.brown.model.domain.TblCart;
import jp.co.internous.brown.model.domain.dto.CartDto;
import jp.co.internous.brown.model.form.CartForm;

@Mapper
public interface TblCartMapper {
	
	//ユーザーIDに紐づくカート情報を取得
	List<CartDto> findByUserId(@Param("id") int id);
	
	
	//ユーザーIDと商品IDに紐づくカート情報を取得
	@Select("SELECT * FROM tbl_cart WHERE user_id = #{id} AND product_id = #{productId}")
	List<TblCart> findByUserIdAndProductId(
			@Param("id") int id,
			@Param("productId") int productId);
	
	
	/*ユーザーIDに紐づくカート情報を更新
	 * （更新する内容:個数）
	 */
	@Update("UPDATE tbl_cart SET product_count = product_count + #{productCount}, updated_at = now() WHERE user_id = #{id} and product_id = #{productId}")
	int tblCartUpdate(CartForm form);
	
	
	//カート情報を登録
	@Insert("INSERT INTO tbl_cart (user_id, product_id, product_count) VALUES (#{id}, #{productId}, #{productCount})")
	int tblCartInsert(CartForm form);
	
	
	//チェックされたカートIDのカート情報を削除
	int deleteCartById(@Param("checkedIds") List<Integer> checkedIds);
	
	
	//ユーザーIDに紐づくカート情報を削除
	@Delete("DELETE FROM tbl_cart WHERE user_id = #{id}")
	int deleteCartByUserId(int id);
	
	
	//ユーザーIDに紐づくカート情報を取得
	@Select("SELECT count(user_id) FROM tbl_cart WHERE user_id = #{id}")
	int findCountByUserId(int id);

	
	//仮ユーザーIDに紐づいているカート情報のユーザーIDを更新
	@Update("UPDATE tbl_cart SET user_id = #{id}, updated_at = now() WHERE user_id = #{tempId}")
	int updateUserId(
			@Param("id") int id,
			@Param("tempId") int tempId);
	
}
