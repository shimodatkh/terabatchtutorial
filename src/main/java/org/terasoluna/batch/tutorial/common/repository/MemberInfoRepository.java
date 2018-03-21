package org.terasoluna.batch.tutorial.common.repository;

import org.terasoluna.batch.tutorial.common.dto.MemberInfoDto;
import java.util.List;

public interface MemberInfoRepository {
	  List<MemberInfoDto> findAll(); // (1)

	  int updatePointAndStatus(MemberInfoDto memberInfo); // (2)
}
