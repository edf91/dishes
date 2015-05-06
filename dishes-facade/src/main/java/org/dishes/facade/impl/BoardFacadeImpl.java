package org.dishes.facade.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.dishes.application.BoardApplication;
import org.dishes.commons.ConstantsValue;
import org.dishes.commons.InvokeResult;
import org.dishes.domain.Board;
import org.dishes.facade.BoardFacade;
import org.dishes.facade.assembler.BoardAssembler;
import org.dishes.facade.command.CreateBoardCommand;
import org.dishes.facade.dto.BoardDTO;
/**
 * 餐桌门面层实现类
 */
@Named
public class BoardFacadeImpl implements BoardFacade{
	@Inject
	private BoardApplication boardApplication;
	
	/**
	 * 添加餐桌
	 */
	public InvokeResult<String> addBoard(CreateBoardCommand command) {
		try {
			if(boardApplication.isNameExists(command.getName())) return InvokeResult.failure(ConstantsValue.ERROR_BOARD_CODE,"餐桌名称已经存在");
			boardApplication.addBoard(BoardAssembler.toEntity(command));
			return InvokeResult.success("添加餐桌成功");
		} catch (Exception e) {
			e.printStackTrace();
			return InvokeResult.failure(ConstantsValue.ERROR_BOARD_CODE,"添加餐桌失败");
		}
		
	}
	/**
	 * 获取餐桌列表
	 */
	public InvokeResult<List<BoardDTO>> listBoard() {
		return InvokeResult.success(BoardAssembler.toDTOs(boardApplication.listBoard()));
	}
	/**
	 * 根据id删除餐桌
	 */
	public InvokeResult<String> deleteBoardById(String boardId) {
		Board board = boardApplication.getBoardById(boardId);
		if(board.isUse()) return InvokeResult.failure(ConstantsValue.ERROR_BOARD_CODE,"餐桌有客不能删除");
		boardApplication.deleteBoardById(boardId);
		return InvokeResult.success("删除"+board.getName()+"成功");
	}
	/**
	 * 根据id获取餐桌
	 */
	public InvokeResult<BoardDTO> getBoardById(String boardId) {
		Board board = boardApplication.getBoardById(boardId);
		if(board == null) return InvokeResult.failure(ConstantsValue.ERROR_BOARD_CODE,"该餐桌不存在");
		return InvokeResult.success(BoardAssembler.toDTO(board));
	}

}
