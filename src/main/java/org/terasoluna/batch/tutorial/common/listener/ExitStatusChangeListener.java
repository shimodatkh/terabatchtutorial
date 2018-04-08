package org.terasoluna.batch.tutorial.common.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class ExitStatusChangeListener implements StepExecutionListener {

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		
		ExitStatus exitStatus = stepExecution.getExitStatus();
		if (conditionalCheck(stepExecution)) {
			exitStatus = new ExitStatus("SKIPPED");
		}
		return exitStatus;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub

	}
	
	private boolean conditionalCheck(StepExecution stepExecution) {
		return (stepExecution.getWriteCount() != stepExecution.getReadCount());
	}

}
