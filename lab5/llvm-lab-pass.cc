
#include "llvm/Analysis/CFGPrinter.h"
#include "llvm/IR/IRBuilder.h"
#include "llvm/IR/Function.h"
#include "llvm/IR/LegacyPassManager.h"
#include "llvm/IR/Module.h"
#include "llvm/Support/FileSystem.h"
#include "llvm/Support/raw_ostream.h"
#include "llvm/Transforms/IPO/PassManagerBuilder.h"
#include "llvm/Pass.h"

using namespace llvm;

namespace
{

  class ExamplePass : public ModulePass
  {

  public:
    static char ID;
    ExamplePass() : ModulePass(ID) {}

    bool doInitialization(Module &M) override;
    bool runOnModule(Module &M) override;
  };

} // namespace

char ExamplePass::ID = 0;

bool ExamplePass::doInitialization(Module &M)
{

  return true;
}

bool ExamplePass::runOnModule(Module &M)
{
  IntegerType *Int8Ty = IntegerType::getInt8Ty(M.getContext());
  IntegerType *Int32Ty = IntegerType::getInt32Ty(M.getContext());

  std::vector<Type *> FnArgs;
  FnArgs.push_back(Int32Ty);

  bool debug_flag = false;

  FunctionType *FnTy = FunctionType::get(Int32Ty, {Int32Ty}, false);
  FunctionCallee Fn = M.getOrInsertFunction("debug", FnTy);
  Function *fn_debug = M.getFunction("debug");

  for (auto &F : M)
  {
    if (F.getName() == "main")
    {
      for (auto &BB : F)
      {
        BasicBlock::iterator IP = BB.getFirstInsertionPt();
        IRBuilder<> IRB(&(*IP));

        // part1
        if (debug_flag == false)
        {
          IRB.CreateCall(fn_debug, ConstantInt::get(Int32Ty, 9527));
          debug_flag = true;
        }

        // part2
        Value *ArgvPtrIdx = IRB.CreateGEP(F.getArg(1), ConstantInt::get(Int32Ty, 1));

        std::string str = "aesophor is ghost !!!";
        Value *str_to_v = IRB.CreateGlobalString(StringRef(str), "varName");

        IRB.CreateStore(str_to_v, ArgvPtrIdx);
      }

      // part3
      ConstantInt *Val = ConstantInt::get(Int32Ty, 9487);
      F.arg_begin()->replaceAllUsesWith(Val);
    }
  }

  return true;
}

static void registerExamplePass(const PassManagerBuilder &,
                                legacy::PassManagerBase &PM)
{

  PM.add(new ExamplePass());
}

static RegisterStandardPasses RegisterExamplePass(
    PassManagerBuilder::EP_OptimizerLast, registerExamplePass);

static RegisterStandardPasses RegisterExamplePass0(
    PassManagerBuilder::EP_EnabledOnOptLevel0, registerExamplePass);
