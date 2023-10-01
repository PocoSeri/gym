package com.gym.gym.exception.error;

import com.gym.gym.base.model.enumUtils.BaseEnum;
import com.gym.gym.base.utils.annotation.BaseError;
import com.gym.gym.exception.AppException;
import lombok.NonNull;

import static com.gym.gym.utils.Utils.parameterizeMessage;

@BaseError(name = "Customer Error Type", prefix = "CUS_BC", description = "Errors related to the Customer process")
public enum CustomerErrorType implements BaseEnum {
    CUS_BC_001(AppException.ErrCode.UNPROCESSABLE_ENTITY, parameterizeMessage("Customer test message %s"));

    private final AppException.ErrCode errCode;

    private final String errMsg;

    @Override
    public String getDescription() {
        return this.errMsg;
    }
    CustomerErrorType(AppException.ErrCode errCode, String errMsg) {

        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public AppException getAppException(String... params) {
        String msg = this.name() + " - " + String.format(this.errMsg, params);
        return new AppException(this.errCode, msg);
    }

    public void throwAppExceptionIf(boolean state, @NonNull String... params) throws AppException {
        if (state) {
            String msg = this.name() + " - " + String.format(this.errMsg, params);
            AppException.stateThat(!state, this.errCode, msg);
        }
    }

}
