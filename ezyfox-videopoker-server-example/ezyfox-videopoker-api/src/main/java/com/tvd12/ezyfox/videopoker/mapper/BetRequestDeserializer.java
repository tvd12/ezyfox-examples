/**
 * 
 */
package com.tvd12.ezyfox.videopoker.mapper;

import com.tvd12.ezyfox.core.serialize.ObjectDeserializer;
import com.tvd12.ezyfox.core.transport.Parameters;
import com.tvd12.ezyfox.videopoker.request.BetRequestListener;

/**
 * @author tavandung12
 *
 */
public class BetRequestDeserializer implements ObjectDeserializer {

    @SuppressWarnings("unchecked")
    @Override
    public BetRequestListener deserialize(Object object, Parameters params) {
        BetRequestListener bet = (BetRequestListener)object;
        bet.setBettingTypeId(params.get("1", int.class));
        return bet;
    }

}
