package org.dishes.facade.assembler;

import java.util.ArrayList;
import java.util.List;

import org.dishes.domain.Board;
import org.dishes.facade.command.CreateBoardCommand;
import org.dishes.facade.dto.BoardDTO;

/**
 * 餐桌装配类
 */
public class BoardAssembler {

	public static Board toEntity(CreateBoardCommand dto) {
		Board result = new Board();
		result.setName(dto.getName());
		return result;
	}
	
	public static BoardDTO toDTO(Board entity){
		BoardDTO dto = new BoardDTO();
		dto.setId(entity.getId());
		dto.setLastOrderId(entity.getLastOrderId());
		dto.setName(entity.getName());
		dto.setUse(entity.isUse());
		return dto;
	}
	public static List<BoardDTO> toDTOs(List<Board> entities) {
		List<BoardDTO> results = new ArrayList<BoardDTO>();
		for (Board entity : entities) {
			results.add(toDTO(entity));
		}
		return results;
	}

}
