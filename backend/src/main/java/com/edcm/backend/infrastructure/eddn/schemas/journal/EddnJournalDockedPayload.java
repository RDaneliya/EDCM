package com.edcm.backend.infrastructure.eddn.schemas.journal;

import com.edcm.backend.infrastructure.eddn.schemas.EddnPayload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EddnJournalDockedPayload extends EddnPayload {
    private EddnJournalDockedMessage message;
}
