package com.sparrows.user.admin.port.in;

import com.sparrows.user.admin.model.entity.FailureEntity;
import com.sparrows.user.admin.model.entity.ReportEntity;

public interface AdminUseCase {
    void submitReport(ReportEntity report);

    void submitFailure(FailureEntity failure);
}
