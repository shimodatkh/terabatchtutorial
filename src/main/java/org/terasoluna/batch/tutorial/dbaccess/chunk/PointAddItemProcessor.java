package org.terasoluna.batch.tutorial.dbaccess.chunk;

import javax.inject.Inject;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.Validator;
import org.springframework.stereotype.Component;
import org.terasoluna.batch.tutorial.common.dto.MemberInfoDto;

@Component // (1)
public class PointAddItemProcessor implements ItemProcessor<MemberInfoDto, MemberInfoDto> {

    private static final String TARGET_STATUS = "1"; // (3)

    private static final String INITIAL_STATUS = "0"; // (4)

    private static final String GOLD_MEMBER = "G"; // (5)

    private static final String NORMAL_MEMBER = "N"; // (6)

    private static final int MAX_POINT = 1000000; // (7)
    
    @Inject // (1)
    Validator<MemberInfoDto> validator; // (2)
    
	@Override
	public MemberInfoDto process(MemberInfoDto item) throws Exception {
		
		validator.validate(item); // (3)
		
		if (TARGET_STATUS.equals(item.getStatus())) {
			if (GOLD_MEMBER.equals(item.getType())) {
				item.setPoint(item.getPoint() + 100);
			} else if (NORMAL_MEMBER.equals(item.getType())) {
				item.setPoint(item.getPoint() + 10);
			}
			
			if (item.getPoint() > MAX_POINT) {
				item.setPoint(MAX_POINT);
			}
			
			item.setStatus(INITIAL_STATUS);	
		}
		
		return item;
	}

}
