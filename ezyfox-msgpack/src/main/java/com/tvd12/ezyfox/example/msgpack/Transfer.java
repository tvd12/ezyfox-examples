package com.tvd12.ezyfox.example.msgpack;

import com.tvd12.ezyfox.binding.annotation.EzyArrayBinding;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@EzyArrayBinding
public class Transfer {
    private int userId;
    private int balance;
}
