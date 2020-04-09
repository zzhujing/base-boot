package zz.hujing.baseboot.core.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 自定义分页对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@Accessors(chain = true)
@Builder
public class MyPage<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @NonNull
    private long pageIndex;
    @NonNull
    private long pageSize;
    private long total;
    private List<T> data;


    public static <T, V> MyPage<T> buildResultWithFunction(IPage<V> source, MyPage<T> result, Function<V, T> convertFunc) {
        result.setTotal(source.getTotal());
        result.setData(source.getRecords().stream().map(convertFunc).collect(Collectors.toList()));
        return result;
    }

    public static <T> MyPage<T> buildResultIdentity(IPage<T> source, MyPage<T> result) {
        result.setTotal(source.getTotal());
        result.setData(source.getRecords());
        return result;
    }

    @JsonIgnore
    public <T> Page<T> getQueryPage() {
        return new Page<>(this.getPageIndex(), this.getPageSize());
    }

}
