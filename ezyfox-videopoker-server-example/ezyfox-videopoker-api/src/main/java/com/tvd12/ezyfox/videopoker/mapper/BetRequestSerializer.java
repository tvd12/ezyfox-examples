/**
 * 
 */
package com.tvd12.ezyfox.videopoker.mapper;

import com.tvd12.ezyfox.core.serialize.ObjectSerializer;
import com.tvd12.ezyfox.core.transport.Parameters;
import com.tvd12.ezyfox.core.transport.impl.ParameterWrapper;
import com.tvd12.ezyfox.videopoker.request.BetRequestListener;

/**
 * @author tavandung12
 *
 */
public class BetRequestSerializer implements ObjectSerializer<BetRequestListener> {

    @Override
    public Parameters serialize(BetRequestListener object) {
        Parameters answer = new ParameterWrapper();
        answer.set("1", object.getBettingTypeId());
        return answer;
    }

}
