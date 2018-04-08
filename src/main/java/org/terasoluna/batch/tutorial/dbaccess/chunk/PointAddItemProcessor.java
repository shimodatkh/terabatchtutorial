package org.terasoluna.batch.tutorial.dbaccess.chunk;

import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.terasoluna.batch.tutorial.common.dto.MemberInfoDto;

@Component // (1)
public class PointAddItemProcessor implements ItemProcessor<MemberInfoDto, MemberInfoDto> {

    private static final String TARGET_STATUS = "1"; // (3)

    private static final String INITIAL_STATUS = "0"; // (4)

    private static final String GOLD_MEMBER = "G"; // (5)

    private static final String NORMAL_MEMBER = "N"; // (6)

    private static final int MAX_POINT = 1000000; // (7)
    
    private static final Logger logger = LoggerFactory.getLogger(PointAddItemProcessor.class); // (1)
    
    @Inject // (1)
    Validator<MemberInfoDto> validator; // (2)
    
    @Inject
    MessageSource messageSource; // (2)

    
	@Override
	public MemberInfoDto process(MemberInfoDto item) throws Exception {
		try {
			validator.validate(item); // (3)
		} catch (ValidationException e) {
			logger.warn(messageSource
                    .getMessage("errors.maxInteger", new String[] { "point(tuto05)", "1000000" }, Locale.getDefault())); // (4)
			return null;
		}
		
		
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
